package github.base

import org.kohsuke.github.GHPullRequest
import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub

class WrapperFactory {
    private final static MAPPING
    static {
        def map = [:].withDefault { Wrapper }
        map[GitHub] = GroovyGitHub
        map[GHRepository] = GHRepositoryWrapper
        map[GHPullRequest] = GHPullRequestWrapper
        MAPPING = map.asImmutable()
    }

    public static Wrapper wrap(o) {
        MAPPING.get(o.class).newInstance(delegate: o)
    }

    public static Iterable<Wrapper> wrap(Iterable o) {
        o.collect { wrap(it) }
    }
}
