package com.getir.readingisgood.constants;

public final class Constants {

    private Constants(){

    }

    public static final class RegEx {

        public static final String NUMERIC_VALIDATION = "^[0-9]*$";

        private static final String VALID_NON_ALPHABETIC_CHARS = "+-_.,'() /\\\\";

        public static final String ALPHABETIC_VALIDATION = "^[\\p{L}" + VALID_NON_ALPHABETIC_CHARS + "]*$";

        public static final String ALPHANUMERIC_VALIDATION = "^[\\p{L}" + VALID_NON_ALPHABETIC_CHARS + "0-9]*$";

        private static final String VALID_EDITORIAL_ALPHABETIC_CHARS = "\n\":;*+-_.,'()% /\\\\";

        public static final String ALPHABETIC_CHARS = "^[\\p{L} ]*$";

        public static final String EDITORIAL_ALPHANUMERIC_VALIDATION = "^[\\p{L}" + VALID_EDITORIAL_ALPHABETIC_CHARS + "0-9]*$";

        private RegEx() {

        }
    }
}
