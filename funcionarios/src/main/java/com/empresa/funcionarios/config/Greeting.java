/**
 * 
 */
package com.empresa.funcionarios.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vinicius
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
