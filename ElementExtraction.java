/*String fremySalt = "K4[ON(SO3)2]2";
parseMolecule.getAtoms(fremySalt); // return ["K": 4, "O": 14, "N": 2, "S": 4]*/
import java.util.*;

public class ElementExtraction {
	public static void main(String[] argv){
		elementExtraction("As2{Be4C5[BCo3(CO2)3]2}4Cu5").entrySet().forEach(entry -> {System.out.println(entry.getKey()+"  "+entry.getValue());});
		System.out.println(validateBrace(")","("));
	}
	public static Map<String,Integer> elementExtraction(String formula){
		Map<String,Integer> formula_element = new HashMap<String,Integer>();
		String formula_array[] = formula.split("");
		formula_array = splitFormula(formula_array);
		int top = formula_array.length,top_parn = -1,value = 1;
		String paranthesis[] = new String[top*2];
		for(int i = top - 1;i >= 0;i--){
			if(formula_array[i].matches("\\d+")){
				//System.out.println("digit"+formula_array[i]);
				value *= Integer.parseInt(formula_array[i]);
			}
			if(formula_array[i].matches("[)}\\]]")){
				if(i + 1 < top && formula_array[i + 1].matches("\\d+")){
					paranthesis[++top_parn] = formula_array[i + 1];	
				}
				else paranthesis[++top_parn] = "1";	
				paranthesis[++top_parn] = formula_array[i];
				//System.out.println("brace C"+formula_array[i]);
			}
			if(formula_array[i].matches("[({\\[]")){
				if(top_parn == -1){
					System.out.println("exit");
					System.exit(0);
				}
				if(validateBrace(paranthesis[top_parn],formula_array[i]) == false){
					System.out.println("invalid brace");
					System.exit(0);
				}
				paranthesis[top_parn] = null;
				top_parn--;
				value /= Integer.parseInt(paranthesis[top_parn]);
				paranthesis[top_parn] = null;
				top_parn--;
				//System.out.println("brace O"+formula_array[i]);
			}
			if(formula_array[i].matches("[A-Z][a-z]?")){
				String element = formula_array[i];
				//System.out.println("element "+element+"value "+value);
				if(formula_element.get(element) == null){
					formula_element.put(element,value);
				}
				else {
					int temp = formula_element.get(element);
					temp += value;
					formula_element.put(element,temp);
				}
				if(i + 1 < top && formula_array[i + 1].matches("\\d+")) {
					value /= Integer.parseInt(formula_array[i + 1]); 
				}
			}
		}
		//for(String s: paranthesis) System.out.println(s);
		if(formula_element.isEmpty() || top_parn != -1) System.out.println("Empty" + top_parn);
		return formula_element;
	}
	//function to split formula based on the element
	public static String[] splitFormula(String[] str1){
		for(int i = 0; i < str1.length;i++) {
			if(str1[i].matches("[A-Z]")) {
				for(int j = i + 1; j < str1.length;j++){
					if(str1[j].matches("[a-z]")){
						str1[i] += str1[j];
						str1[j] = "";
					}
					else break;
				}
			}
			if(str1[i].matches("\\d")) {
				for(int j = i + 1; j < str1.length;j++){
					if(str1[j].matches("\\d")){
						str1[i] += str1[j];
						str1[j] = "";
					}
					else break;
				}
			}
		 }
		 str1 = Arrays.stream(str1).filter(s -> s != "").toArray(String[]::new);
		 return str1;
	}
	public static boolean validateBrace(String br1, String br2) {
		switch(br1){
			case ")" : if(br2.equals("(")) return true;
						break;
			case "]" : if(br2.equals("[")) return true;
						break;
			case "}" : if(br2.equals("{")) return true;
						break;				
			default : System.out.println(br1 + br2);
						break;
			
		}
		return false;
	}
}



/*
BEST PRACTICE
import java.util.*;
import java.util.regex.*;

class ParseMolecule {
    
    private static Iterator<String> tokenIter;
    private static Stack<Integer> bracketStk;
    
    private static final String  AT_NUM    = "[A-Z][a-z]?\\d*";
    private static final String  OPEN_BRA  = "[{(\\[]";
    private static final String  CLOSE_BRA = "[)}\\]]\\d*";
    private static final Pattern TOKENIZER = Pattern.compile(String.format("%s|%s|%s", AT_NUM, OPEN_BRA, CLOSE_BRA));
    private static final Pattern P_AT_NUM  = Pattern.compile("(?<at>[A-Z][a-z]*)(?<num>\\d*)");
    
    
    public static Map<String,Integer> getAtoms(String formula) {
        
        List<String> tokens = new ArrayList<String>();
        Matcher m = TOKENIZER.matcher(formula);
        while (m.find()) tokens.add(m.group());
        
        if (!String.join("", tokens).equals(formula)) throw new IllegalArgumentException("Invalid formula");
        
        bracketStk = new Stack<Integer>();
        tokenIter  = tokens.iterator();
        
        RawForm ans = getRawFormula();
        if (!bracketStk.empty()) throw new IllegalArgumentException("Invalid formula");
        
        return ans;
    }
    
    
    private static RawForm getRawFormula() {
        
        RawForm raw = new RawForm();
        
        while (tokenIter.hasNext()) {
            String tok = tokenIter.next();
            
            if (tok.matches(OPEN_BRA)) {
                bracketStk.push((int) tok.charAt(0));
                raw.concatWith(getRawFormula());
            
            } else if (tok.matches(AT_NUM)) {
                Matcher m = P_AT_NUM.matcher(tok);
                m.find();
                raw.addAtom(m.group("at"), m.group("num").isEmpty() ? 1 : Integer.parseInt(m.group("num")));
                
            } else if (tok.matches(CLOSE_BRA)) {
                if (bracketStk.empty() || (bracketStk.peek()+1 != (int) tok.charAt(0) && bracketStk.peek()+2 != (int) tok.charAt(0)))
                    throw new IllegalArgumentException("Invalid formula");
                bracketStk.pop();
                
                if (tok.length() > 1) raw.mulBy(Integer.parseInt(tok.substring(1)));
                break;
            }
        }
        return raw;
    }
    
    static class RawForm extends HashMap<String,Integer> {
        public RawForm() { super(); }
        
        public void addAtom(final String atom, final int n) { this.put(atom, n + this.getOrDefault(atom, 0)); }
        public RawForm mulBy(final int n)                   { this.forEach(  (at,v) -> this.put(at, v*n) ); return this; }
        public RawForm concatWith(RawForm other)            { other.forEach( (at,v) -> this.put(at, v + this.getOrDefault(at, 0))  ); return this; }
    }
}
*/
