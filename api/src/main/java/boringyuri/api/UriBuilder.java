/*
 * Copyright 2020 Anton Novikau
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boringyuri.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * An annotation for a builder method that constructs a {@code Uri} based on the given
 * method parameters.
 * </p>
 * <p>
 * Example:
 * <pre><code>
 *     &#64UriBuilder("user")
 *     public Uri buildFetchUserDetailsUri(&#64Path String group, &#64Param("id") int userId);
 * </code></pre>
 * Calling {@code foo.buildFetchUserDetailsUri("friends", 42)} yields {@code /user/friends?id=42}
 * </p>
 *
 * @see UriFactory
 * @see WithUriData
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface UriBuilder {
    /**
     * Relative {@code Uri} path.
     *
     * <p>If the path doesn't start with a '/', the builder will prepend the
     * given path with a '/'.
     */
    String value() default "";

    /**
     * Specifies whether the base path value to the builder method
     * is already URL encoded. Default is <code>true</code>.
     */
    boolean encoded() default true;
}