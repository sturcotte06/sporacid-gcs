package gcs.webapp.utils.app.messages;

/**
 * An interface to force localization
 * @author Simon Turcotte-Langevin
 */
public interface ILocalizable 
{
	/**
	 * Localize the object strings in the default locale
	 * @param localizer An object to localize strings
	 */
	public void localize(IMessageLocalizer localizer);
	/**
	 * Localize the object strings in the given locale
	 * @param localizer An object to localize strings
	 * @param locale The lcoal in which to localize
	 */
	public void localize(IMessageLocalizer localizer, String locale);
}
