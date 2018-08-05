package github.base

import org.codehaus.groovy.runtime.MetaClassHelper

class Wrapper {
    def delegate

    def methodMissing(String name, args) {
        try {
            delegate.invokeMethod(name, args)
        } catch (MissingMethodException e) {
            if (args.length == 2 && args[-1] instanceof Closure) {
                def metaMethod = delegate.metaClass.getMetaMethod("get${MetaClassHelper.capitalize(name)}", args[0])
                if (metaMethod) {
                    // ex: organization('foo') { ... } ---> getOrganization('foo').with { ... }
                    def result = WrapperFactory.wrap(metaMethod.invoke(delegate, args[0]))
                    result.with(args[1])
                }
            } else if (args.length > 0) {
                def metaMethod = delegate.metaClass.getMetaMethod("get${MetaClassHelper.capitalize(name)}", args)
                if (metaMethod) {
                    // ex: getPullRequests('foo') ---> pullRequests('foo')
                    def result = metaMethod.invoke(delegate, args)
                    if (result instanceof List) {
                        result = result.collect { WrapperFactory.wrap(it) }
                    }
                    result
                }
            } else {
                throw e
            }
        }
    }

    def propertyMissing(String name) {
        delegate."$name"
    }
}
