package gcs.website.views.helpers;

public class HtmlString 
{
	private String htmlString;

	public HtmlString(String htmlString)
	{
		this.htmlString = htmlString;
	}
	
	public String getHtmlString() 
	{
		return htmlString;
	}
	
	public static String htmlEncode(String toEncode)
	{
		StringBuffer out = new StringBuffer();
	    for(int i = 0; i < toEncode.length(); i++)
	    {
	        char c = toEncode.charAt(i);
	        if(c > 127 || c=='"' || c=='<' || c=='>') {
	           out.append("&#"+(int)c+";");
	        }
	        else {
	            out.append(c);
	        }
	    }
	    return out.toString();
	}
}
