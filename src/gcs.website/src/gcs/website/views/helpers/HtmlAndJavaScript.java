package gcs.website.views.helpers;

public class HtmlAndJavaScript extends HtmlString
{
    private String script;

    public HtmlAndJavaScript(String htmlString, String script)
    {
        super(htmlString);
        this.script = script;
    }

    public String getScript()
    {
        return script;
    }
}
