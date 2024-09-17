/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author Xico
 */
public class InvalidProjectNameException extends Exception {
    public InvalidProjectNameException() {
    }

    public InvalidProjectNameException(String message) {
        super(message);
    }
}
