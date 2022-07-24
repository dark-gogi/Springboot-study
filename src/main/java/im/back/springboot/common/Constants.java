package im.back.springboot.common;

public class Constants {

    public enum ExceptionClass{

        PRODUCT("Product");

        private final String exceptionClass;

        ExceptionClass(String exceptionClass){
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass(){
            return this.exceptionClass;
        }

        @Override
        public String toString(){
            return getExceptionClass() + " Exception";

        }
    }


}
