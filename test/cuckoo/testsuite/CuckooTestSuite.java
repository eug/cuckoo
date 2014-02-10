
package cuckoo.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    cuckoo.testsuite.ExamplesFinite.class,
    cuckoo.testsuite.ExamplesPushdown.class
})
public class CuckooTestSuite {}
