package com.pranit.notification.email.helper;


public final class MaskData {
    private MaskData() {
    }

    public static String maskEmail(String email) {
        if (email == null || email.isBlank()) return email;
        int atIndex = email.indexOf('@');
        if (atIndex <= 1) return "***" + email.substring(atIndex);
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex);
        return username.charAt(0)
               + "*".repeat(Math.max(1, username.length() - 2))
               + username.charAt(username.length() - 1)
               + domain;
    }
}
