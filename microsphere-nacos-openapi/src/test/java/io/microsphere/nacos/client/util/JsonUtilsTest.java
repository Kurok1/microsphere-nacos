/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.microsphere.nacos.client.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.microsphere.nacos.client.util.JsonUtils.toJSON;
import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link JsonUtils} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see JsonUtils
 * @since 1.0.0
 */
public class JsonUtilsTest {

    @Test
    public void testMapToJSON() {
        Map<String, String> map = new HashMap<>();
        map.put("key-1", "value-1");
        map.put("key-2", "value-2");

        String json = toJSON(map);
        assertEquals("{\"key-1\":\"value-1\",\"key-2\":\"value-2\"}", json);

        json = toJSON(emptyMap());
        assertEquals("{}", json);
    }

    @Test
    public void testArrayToJSON() {
        String[] elements = new String[]{"a", "b", "c"};
        String json = toJSON(elements);
        assertEquals("[\"a\",\"b\",\"c\"]", json);
    }
}
