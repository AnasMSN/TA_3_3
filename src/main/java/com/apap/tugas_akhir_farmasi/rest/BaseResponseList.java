package com.apap.tugas_akhir_farmasi.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponseList<T> {

	/**
	 * BaseResponse
	 */
    private int status;
    private String message;
    @JsonProperty("result")
    private List<T> result;
    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @return the result
     */


    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @param result the result to set
     */
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}

    
}
