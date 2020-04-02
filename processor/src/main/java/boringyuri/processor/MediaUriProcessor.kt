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
package boringyuri.processor

import boringyuri.api.Param
import boringyuri.api.Path
import boringyuri.api.UriBuilder
import boringyuri.api.WithUriData
import boringyuri.api.adapter.TypeAdapter
import boringyuri.api.constant.*
import boringyuri.processor.base.BoringAnnotationProcessor
import boringyuri.processor.base.BoringProcessingStep
import boringyuri.processor.base.ProcessingSession
import boringyuri.processor.util.AnnotationHandler
import com.google.auto.service.AutoService
import com.google.common.collect.ImmutableSet
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName
import net.ltgt.gradle.incap.IncrementalAnnotationProcessor
import net.ltgt.gradle.incap.IncrementalAnnotationProcessorType
import javax.annotation.processing.Processor
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion

@Suppress("unused") // class is used by @AutoService
@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@IncrementalAnnotationProcessor(IncrementalAnnotationProcessorType.ISOLATING)
class MediaUriProcessor : BoringAnnotationProcessor() {
    override fun initSteps(session: ProcessingSession): Iterable<BoringProcessingStep> {
        val annotationHandler = AnnotationHandler(INTERNAL_ANNOTATIONS)

        return ImmutableSet.of(
            AssociatedUriDataGeneratorStep(session, annotationHandler),
            MediaUriGeneratorStep(session, annotationHandler)
        )
    }

    private companion object {
        val INTERNAL_ANNOTATIONS: Set<TypeName> = hashSetOf(
            ClassName.get(UriBuilder::class.java),
            ClassName.get(WithUriData::class.java),
            ClassName.get(TypeAdapter::class.java),
            ClassName.get(Path::class.java),
            ClassName.get(Param::class.java),
            ClassName.get(StringParam::class.java),
            ClassName.get(StringParams::class.java),
            ClassName.get(LongParam::class.java),
            ClassName.get(LongParams::class.java),
            ClassName.get(DoubleParam::class.java),
            ClassName.get(DoubleParams::class.java),
            ClassName.get(BooleanParam::class.java),
            ClassName.get(BooleanParams::class.java)
        )
    }
}