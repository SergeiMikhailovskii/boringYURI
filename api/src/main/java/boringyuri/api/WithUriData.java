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
 * An annotation that indicates to generate a data class for a {@code Uri}, so all the method
 * parameters serialized into path segments and query parameters can be deserialized back and
 * used in the code in an object representation.
 * </p>
 * <p>
 * Example:
 * <pre><code>
 *     &#64UriBuilder("user/create")
 *     &#64WithUriData
 *     Uri buildCreateUserUri(&#64Path long id, &#64Param String name, &#64Param String phoneNumber);
 * </code></pre>
 * For the {@code Uri} above there going to be generated the following data class:
 * <pre><code>
 *     public final class CreateUserData {
 *         &#64NonNull
 *         private final Uri mDataUri;
 *
 *         ...
 *
 *         private long mId;
 *
 *         private String mName;
 *
 *         private String mPhoneNumber;
 *
 *         public CreateUserData(&#64NonNull Uri uri) {
 *             mDataUri = uri;
 *         }
 *
 *         public long getId() {
 *             ...
 *             return mId;
 *         }
 *
 *         public String getName() {
 *             ...
 *             return mName;
 *         }
 *
 *         public String getPhoneNumber() {
 *             ...
 *             return mPhoneNumber;
 *         }
 *     }
 * </code></pre>
 * Every field will be obtained from an appropriate query parameter of a path segment and converted
 * from {@code String} into appropriate type if the type conversion is defined: the parameter is
 * of a standard type or a {@link boringyuri.api.adapter.BoringTypeAdapter BoringTypeAdapter}
 * implementation is registered.
 * </p>
 * <p>
 * All method parameter annotations are copied to the getter methods and if the nullability is
 * defined for the parameter it will be validated when when it is parsed from the {@code Uri}.
 * </p>
 *
 * @see UriData
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface WithUriData {
}