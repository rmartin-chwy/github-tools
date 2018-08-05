package github.base

import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub

class WrapperFactory {
    private final static MAPPING
    static {
        def map = [:].withDefault { Wrapper }
        map[GitHub] = GroovyGitHub
        map[GHRepository] = GHRepositoryWrapper
        MAPPING = map.asImmutable()
    }

    public static Wrapper wrap(o) {
        MAPPING.get(o.class).newInstance(delegate: o)
    }
}
