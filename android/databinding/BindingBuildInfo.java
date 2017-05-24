package android.databinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface BindingBuildInfo {
    String buildId();

    boolean enableDebugLogs() default false;

    String exportClassListTo();

    boolean isLibrary();

    String layoutInfoDir();

    int minSdk();

    String modulePackage();

    boolean printEncodedError() default false;

    String sdkRoot();
}
