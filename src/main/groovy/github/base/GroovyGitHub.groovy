package github.base

import org.kohsuke.github.GitHub

class GroovyGitHub extends Wrapper {
    public static void session(Closure c) {
        def gh = new GroovyGitHub(delegate: GitHub.connect())
        def beforeLimit = gh.rateLimit
        gh.with(c)
        def afterLimit = gh.rateLimit
        println "Consumed ${beforeLimit.remaining - afterLimit.remaining} API calls. Remaining: ${afterLimit.remaining}"
    }
}
