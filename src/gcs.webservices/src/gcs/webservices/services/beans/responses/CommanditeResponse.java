package gcs.webservices.services.beans.responses;

import gcs.webservices.models.Commandite;

import java.util.Collection;

public class CommanditeResponse extends Response
{
	private Collection<Commandite> commandites;

	/**
	 * @return the commandites
	 */
	public Collection<Commandite> getCommandites() {
		return commandites;
	}

	/**
	 * @param commandites the commandites to set
	 */
	public void setCommandites(Collection<Commandite> commandites) {
		this.commandites = commandites;
	}
}
