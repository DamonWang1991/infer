/*
* Copyright (c) 2013- Facebook.
* All rights reserved.
*/

package endtoend.c;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.matchers.ResultContainsExactly.containsExactly;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import utils.InferException;
import utils.InferResults;

public class AssertionFailureTest {

  public static final String SOURCE_FILE =
      "assertions/assertion_failure.c";

  public static final String ASSERTION_FAILURE = "Assertion_failure";

  private static InferResults inferResults;

  @BeforeClass
  public static void runInfer() throws InterruptedException, IOException {
    inferResults = InferResults.loadCInferResults(AssertionFailureTest.class, SOURCE_FILE);
  }

  @Test
  public void whenRunsOnAssertionFailureThenAssertionFailureIsFound()
      throws InterruptedException, IOException, InferException {
    String[] methods = {
        "simple_assertion_failure",
        "should_report_assertion_failure",
        "assertion_failure_with_heap",
        "assignemt_before_check",
    };
    assertThat(
        "Results should contain " + ASSERTION_FAILURE,
        inferResults,
        containsExactly(
            ASSERTION_FAILURE,
            SOURCE_FILE,
            methods
        )
    );
  }

}
