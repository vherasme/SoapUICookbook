/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.cxf.jaxrs.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.BindingPriority;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.NameBinding;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Providers;

import org.apache.cxf.common.i18n.BundleUtils;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;

public final class AnnotationUtils {

    private static final Logger LOG = LogUtils.getL7dLogger(AnnotationUtils.class);
    private static final ResourceBundle BUNDLE = BundleUtils.getBundle(AnnotationUtils.class);

    private static final Set<Class<?>> CONTEXT_CLASSES;
    private static final Set<Class<?>> PARAM_ANNOTATION_CLASSES;
    private static final Set<Class<?>> METHOD_ANNOTATION_CLASSES;
    static {
        CONTEXT_CLASSES = initContextClasses();
        PARAM_ANNOTATION_CLASSES = initParamAnnotationClasses();
        METHOD_ANNOTATION_CLASSES = initMethodAnnotationClasses();
    }

    private AnnotationUtils() {

    }

    private static Set<Class<?>> initContextClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(UriInfo.class);
        classes.add(SecurityContext.class);
        classes.add(HttpHeaders.class);
        classes.add(ContextResolver.class);
        classes.add(Providers.class);
        classes.add(Request.class);
        classes.add(ResourceInfo.class);
        classes.add(ResourceContext.class);
        classes.add(Application.class);
        // Servlet API
        try {
            classes.add(HttpServletRequest.class);
            classes.add(HttpServletResponse.class);
            classes.add(ServletConfig.class);
            classes.add(ServletContext.class);
        } catch (Throwable ex) {
            // it is not a problem on the client side and the exception will be
            // thrown later on if the injection of one of these contexts will be
            // attempted on the server side
            LOG.fine(new org.apache.cxf.common.i18n.Message("NO_SERVLET_API", BUNDLE).toString());
        }
        // CXF-specific
        classes.add(MessageContext.class);
        return classes;
    }

    private static Set<Class<?>> initParamAnnotationClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(PathParam.class);
        classes.add(QueryParam.class);
        classes.add(MatrixParam.class);
        classes.add(HeaderParam.class);
        classes.add(CookieParam.class);
        classes.add(FormParam.class);
        classes.add(BeanParam.class);
        return classes;
    }

    private static Set<Class<?>> initMethodAnnotationClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(HttpMethod.class);
        classes.add(Path.class);
        classes.add(Produces.class);
        classes.add(Consumes.class);
        return classes;
    }

    public static int getBindingPriority(Class<?> providerCls) {
        BindingPriority b = providerCls.getAnnotation(BindingPriority.class);
        return b == null ? BindingPriority.USER : b.value();
    }
    public static List<String> getNameBindings(Annotation[] targetAnns) {
        if (targetAnns.length == 0) {
            return Collections.emptyList();
        }
        List<String> names = new LinkedList<String>();
        for (Annotation a : targetAnns) {
            NameBinding nb = a.annotationType().getAnnotation(NameBinding.class);
            if (nb != null) {
                names.add(a.annotationType().getName());
            }
        }
        return names;
    }
    
    public static boolean isContextClass(Class<?> contextClass) {
        return CONTEXT_CLASSES.contains(contextClass);
    }

    public static boolean isParamAnnotationClass(Class<?> annotationClass) {
        return PARAM_ANNOTATION_CLASSES.contains(annotationClass);
    }

    public static boolean isValidParamAnnotationClass(Class<?> annotationClass) {
        return PARAM_ANNOTATION_CLASSES.contains(annotationClass) || Context.class == annotationClass;
    }

    public static boolean isValidParamAnnotations(Annotation[] paramAnnotations) {
        for (Annotation a : paramAnnotations) {
            if (AnnotationUtils.isValidParamAnnotationClass(a.annotationType())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMethodAnnotation(Annotation a) {
        return METHOD_ANNOTATION_CLASSES.contains(a.annotationType())
               || a.annotationType().getAnnotation(HttpMethod.class) != null;
    }

    public static String getAnnotationValue(Annotation a) {
        String value = null;
        if (a.annotationType() == PathParam.class) {
            value = ((PathParam)a).value();
        } else if (a.annotationType() == QueryParam.class) {
            value = ((QueryParam)a).value();
        } else if (a.annotationType() == MatrixParam.class) {
            value = ((MatrixParam)a).value();
        } else if (a.annotationType() == HeaderParam.class) {
            value = ((HeaderParam)a).value();
        } else if (a.annotationType() == CookieParam.class) {
            value = ((CookieParam)a).value();
        } else if (a.annotationType() == FormParam.class) {
            value = ((FormParam)a).value();
        }
        return value;
    }

    public static <T> T getAnnotation(Annotation[] anns, Class<T> type) {
        if (anns == null) {
            return null;
        }
        for (Annotation a : anns) {
            if (a.annotationType() == type) {
                return type.cast(a);
            }
        }
        return null;
    }

    public static Method getAnnotatedMethod(Method m) {
        Method annotatedMethod = doGetAnnotatedMethod(m);
        return annotatedMethod == null ? m : annotatedMethod;
    }

    private static Method doGetAnnotatedMethod(Method m) {

        if (m == null) {
            return m;
        }

        for (Annotation a : m.getAnnotations()) {
            if (AnnotationUtils.isMethodAnnotation(a)) {
                return m;
            }
        }
        for (Annotation[] paramAnnotations : m.getParameterAnnotations()) {
            if (isValidParamAnnotations(paramAnnotations)) {
                return m;
            }
        }

        Class<?> superC = m.getDeclaringClass().getSuperclass();
        if (superC != null && Object.class != superC) {
            try {
                Method method = doGetAnnotatedMethod(superC.getMethod(m.getName(), m.getParameterTypes()));
                if (method != null) {
                    return method;
                }
            } catch (NoSuchMethodException ex) {
                // ignore
            }
        }
        for (Class<?> i : m.getDeclaringClass().getInterfaces()) {
            try {
                Method method = doGetAnnotatedMethod(i.getMethod(m.getName(), m.getParameterTypes()));
                if (method != null) {
                    return method;
                }
            } catch (NoSuchMethodException ex) {
                // ignore
            }
        }

        return null;
    }

    public static String getHttpMethodValue(Method m) {
        for (Annotation a : m.getAnnotations()) {
            HttpMethod httpM = a.annotationType().getAnnotation(HttpMethod.class);
            if (httpM != null) {
                return httpM.value();
            }
        }
        return null;
    }

    public static <A extends Annotation> A getMethodAnnotation(Method m, Class<A> aClass) {
        return m == null ? null : m.getAnnotation(aClass);
    }

    public static <A extends Annotation> A getClassAnnotation(Class<?> c, Class<A> aClass) {
        if (c == null) {
            return null;
        }
        A p = c.getAnnotation(aClass);
        if (p != null) {
            return p;
        }

        p = getClassAnnotation(c.getSuperclass(), aClass);
        if (p != null) {
            return p;
        }

        // finally try the first one on the interface
        for (Class<?> i : c.getInterfaces()) {
            p = getClassAnnotation(i, aClass);
            if (p != null) {
                return p;
            }
        }
        return null;
    }

    public static String getDefaultParameterValue(Annotation[] anns) {

        DefaultValue dv = AnnotationUtils.getAnnotation(anns, DefaultValue.class);
        return dv != null ? dv.value() : null;
    }

}
