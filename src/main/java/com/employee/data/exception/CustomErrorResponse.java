package com.employee.data.exception;


import lombok.Data;


@Data


public class CustomErrorResponse {

	private int statusCode;
    private String message;
 
    public CustomErrorResponse(int statusCode, String message)
    {
        super();
        this.message = message;
    }
}
