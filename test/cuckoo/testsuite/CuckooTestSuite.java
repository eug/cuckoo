
package cuckoo.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author eugf
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    cuckoo.testsuite.ExamplesFinite.class,
    cuckoo.testsuite.ExamplesPushdown.class
})
public class CuckooTestSuite {}
