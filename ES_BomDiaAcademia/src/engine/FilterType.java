package engine;
/**
 * 
 * @author Rafael Dias
 *
 */
public enum FilterType {

	KEYWORD,DATE_CRESCENT,DATE_DECRESCENT,NONE;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getFilterValue();
	}
	
	public String getFilterValue() {
		switch(this) {
		case KEYWORD: 
			return "Keyword";
		case DATE_CRESCENT:
			return "Older";
		case DATE_DECRESCENT:
			return "Recent";
		default:
			return "None";
		}
			
	}
}
