package com.knu_library.main;

public class KLConstants {
	/**
	 * Singleton
	 */
	KLConstants m_instance;
	
	/**
	 * Variables
	 */
	
	// Shared Preference
	public static final String SHARED_PREFERENCE = "SHARED_PREFERENCE";
	
	// Login
	public static final String AUTO_LOGIN = "AUTO_LOGIN";
	public static final String STUDENT_ID = "STUDENT_ID";
	public static final String PASSWORD = "PASSWORD";
	
	public KLConstants getInstance() {
		if (m_instance == null) {
			m_instance = new KLConstants();
		}
		
		return m_instance;
	}
}
