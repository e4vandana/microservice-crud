package io.github.vandana.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class MessageDetail.
 *
 * @author Vandana_Emmadi
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageDetail implements Serializable {

	/**
	 * long MessageDetail.java serialVersionUID
	 */
	private static final long serialVersionUID = 3717598444155728761L;

	/** The status code. */
	private String statusCode;

	/** The status. */
	private String status;

	/** The status message. */
	private String statusMessage;

	/**
	 * Gets the status code.
	 *
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the status message.
	 *
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * Sets the status message.
	 *
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
