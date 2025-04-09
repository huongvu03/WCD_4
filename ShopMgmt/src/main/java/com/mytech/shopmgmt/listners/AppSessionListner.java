package com.mytech.shopmgmt.listners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AppSessionListner implements HttpSessionListener {

	public static int SESSION_COUNT =0;
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		SESSION_COUNT++;
		System.out.println("Current active session: "+ SESSION_COUNT);
		HttpSessionListener.super.sessionCreated(se);
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("Destroy! Current active session:"+ --SESSION_COUNT);
		HttpSessionListener.super.sessionDestroyed(se);
	}
}
