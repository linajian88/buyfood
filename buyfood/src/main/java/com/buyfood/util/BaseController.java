package com.buyfood.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class BaseController implements Serializable {

	private static final long serialVersionUID = 8244392221127655858L;

	public Logger logger = LoggerFactory.getLogger(this.getClass());



}
