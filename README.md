# quarkus-test-mock-bug 

In the project root, execute:

    ./mvnw test

The test [`FailingTest`](./src/test/java/de/turing85/FailingTest.java) will fail, since the expected call to 
`FooRepository` did not occur. Looking into the maven logs, we find:

    ...
    [INFO] -------------------------------------------------------
    [INFO]  T E S T S
    [INFO] -------------------------------------------------------
    [INFO] Running de.turing85.FailingTest
    2021-04-15 17:27:02,443 INFO  [io.quarkus] (main) Quarkus 1.12.2.Final on JVM started in 4.114s. Listening on: http://localhost:8081
    2021-04-15 17:27:02,446 INFO  [io.quarkus] (main) Profile test activated.
    2021-04-15 17:27:02,446 INFO  [io.quarkus] (main) Installed features: [agroal, cdi, config-yaml, hibernate-orm, hibernate-orm-panache, jdbc-postgresql, mutiny, narayana-jta, resteasy, resteasy-jackson, smallrye-context-propagation, spring-data-jpa, spring-di]
    2021-04-15 17:27:04,660 ERROR [io.qua.ver.htt.run.QuarkusErrorHandler] (executor-thread-1) HTTP Request to /foo failed, error id: 408d728a-db93-45d8-b6fb-7175e7b6f316-1: org.jboss.resteasy.spi.UnhandledException: java.lang.ClassCastException: class org.mockito.codegen.FooRepository$MockitoMock$737302548 cannot be cast to class de.turing85.FooRepository_ea854414eddad8795386e3af46a7c577f74e89b6Impl (org.mockito.codegen.FooRepository$MockitoMock$737302548 is in unnamed module of loader net.bytebuddy.dynamic.loading.MultipleParentClassLoader @2ac05a33; de.turing85.FooRepository_ea854414eddad8795386e3af46a7c577f74e89b6Impl is in unnamed module of loader io.quarkus.bootstrap.classloading.QuarkusClassLoader @236e3f4e)
    at org.jboss.resteasy.core.ExceptionHandler.handleApplicationException(ExceptionHandler.java:106)
    at org.jboss.resteasy.core.ExceptionHandler.handleException(ExceptionHandler.java:372)
    at org.jboss.resteasy.core.SynchronousDispatcher.writeException(SynchronousDispatcher.java:218)
    at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:519)
    at org.jboss.resteasy.core.SynchronousDispatcher.lambda$invoke$4(SynchronousDispatcher.java:261)
    at org.jboss.resteasy.core.SynchronousDispatcher.lambda$preprocess$0(SynchronousDispatcher.java:161)
    at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:364)
    at org.jboss.resteasy.core.SynchronousDispatcher.preprocess(SynchronousDispatcher.java:164)
    at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:247)
    at io.quarkus.resteasy.runtime.standalone.RequestDispatcher.service(RequestDispatcher.java:73)
    at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler.dispatch(VertxRequestHandler.java:138)
    at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler.access$000(VertxRequestHandler.java:41)
    at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler$1.run(VertxRequestHandler.java:93)
    at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2415)
    at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1452)
    at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
    at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
    at java.base/java.lang.Thread.run(Thread.java:834)
    at org.jboss.threads.JBossThread.run(JBossThread.java:501)
    Caused by: java.lang.ClassCastException: class org.mockito.codegen.FooRepository$MockitoMock$737302548 cannot be cast to class de.turing85.FooRepository_ea854414eddad8795386e3af46a7c577f74e89b6Impl (org.mockito.codegen.FooRepository$MockitoMock$737302548 is in unnamed module of loader net.bytebuddy.dynamic.loading.MultipleParentClassLoader @2ac05a33; de.turing85.FooRepository_ea854414eddad8795386e3af46a7c577f74e89b6Impl is in unnamed module of loader io.quarkus.bootstrap.classloading.QuarkusClassLoader @236e3f4e)
    at de.turing85.FooRepository_ea854414eddad8795386e3af46a7c577f74e89b6Impl_ClientProxy.arc$delegate(FooRepository_ea854414eddad8795386e3af46a7c577f74e89b6Impl_ClientProxy.zig:69)
    at de.turing85.FooRepository_ea854414eddad8795386e3af46a7c577f74e89b6Impl_ClientProxy.save(FooRepository_ea854414eddad8795386e3af46a7c577f74e89b6Impl_ClientProxy.zig:599)
    at de.turing85.FooResource.foo(FooResource.java:20)
    at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.base/java.lang.reflect.Method.invoke(Method.java:566)
    at org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:170)
    at org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:130)
    at org.jboss.resteasy.core.ResourceMethodInvoker.internalInvokeOnTarget(ResourceMethodInvoker.java:643)
    at org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTargetAfterFilter(ResourceMethodInvoker.java:507)
    at org.jboss.resteasy.core.ResourceMethodInvoker.lambda$invokeOnTarget$2(ResourceMethodInvoker.java:457)
    at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:364)
    at org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTarget(ResourceMethodInvoker.java:459)
    at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:419)
    at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:393)
    at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:68)
    at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:492)
    ... 15 more
    [ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 6.928 s <<< FAILURE! - in de.turing85.FailingTest
    [ERROR] de.turing85.FailingTest.test  Time elapsed: 2.101 s  <<< FAILURE!
    ...