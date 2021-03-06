/**
 *
 *    Copyright 2016-2017, 2019, Optimizely and contributors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.optimizely.ab.event.internal.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.optimizely.ab.event.internal.payload.EventBatch;

import org.junit.Test;

import java.io.IOException;

import static com.optimizely.ab.event.internal.serializer.SerializerTestUtils.generateConversion;
import static com.optimizely.ab.event.internal.serializer.SerializerTestUtils.generateConversionJson;
import static com.optimizely.ab.event.internal.serializer.SerializerTestUtils.generateConversionWithSessionId;
import static com.optimizely.ab.event.internal.serializer.SerializerTestUtils.generateConversionWithSessionIdJson;
import static com.optimizely.ab.event.internal.serializer.SerializerTestUtils.generateImpression;
import static com.optimizely.ab.event.internal.serializer.SerializerTestUtils.generateImpressionJson;
import static com.optimizely.ab.event.internal.serializer.SerializerTestUtils.generateImpressionWithSessionId;
import static com.optimizely.ab.event.internal.serializer.SerializerTestUtils.generateImpressionWithSessionIdJson;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JacksonSerializerTest {

    private JacksonSerializer serializer = new JacksonSerializer();
    private ObjectMapper mapper =
        new ObjectMapper().setPropertyNamingStrategy(
            PropertyNamingStrategy.SNAKE_CASE);


    @Test
    public void serializeImpression() throws IOException {
        EventBatch impression = generateImpression();
        // can't compare JSON strings since orders could vary so compare objects instead
        EventBatch actual = mapper.readValue(serializer.serialize(impression), EventBatch.class);
        EventBatch expected = mapper.readValue(generateImpressionJson(), EventBatch.class);

        assertThat(actual, is(expected));
    }

    @Test
    public void serializeImpressionWithSessionId() throws IOException {
        EventBatch impression = generateImpressionWithSessionId();
        // can't compare JSON strings since orders could vary so compare objects instead
        EventBatch actual = mapper.readValue(serializer.serialize(impression), EventBatch.class);
        EventBatch expected = mapper.readValue(generateImpressionWithSessionIdJson(), EventBatch.class);

        assertThat(actual, is(expected));
    }

    @Test
    public void serializeConversion() throws IOException {
        EventBatch conversion = generateConversion();
        // can't compare JSON strings since orders could vary so compare objects instead
        EventBatch actual = mapper.readValue(serializer.serialize(conversion), EventBatch.class);
        EventBatch expected = mapper.readValue(generateConversionJson(), EventBatch.class);

        assertThat(actual, is(expected));
    }

    @Test
    public void serializeConversionWithSessionId() throws IOException {
        EventBatch conversion = generateConversionWithSessionId();
        // can't compare JSON strings since orders could vary so compare objects instead
        EventBatch actual = mapper.readValue(serializer.serialize(conversion), EventBatch.class);
        EventBatch expected = mapper.readValue(generateConversionWithSessionIdJson(), EventBatch.class);

        assertThat(actual, is(expected));
    }
}
