package com.github.jinahya.bit.io;

/*-
 * #%L
 * bit-io2
 * %%
 * Copyright (C) 2020 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

/**
 * A class for converting an instance of {@link ByteOutput} to an instance of {@link BitOutput}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ByteInput2BitInputConverter
 */
class ByteOutput2BitOutputConverter
        implements ArgumentConverter {

    @Override
    public Object convert(final Object source, final ParameterContext context) throws ArgumentConversionException {
        if (!(source instanceof ByteOutput)) {
            throw new ArgumentConversionException(
                    "can't convert " + source + " into an instance of " + BitOutput.class);
        }
        return new ByteOutputAdapter((ByteOutput) source);
    }
}
