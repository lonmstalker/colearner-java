package io.lonmstalker.colearner.constants;

import com.google.common.base.Splitter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.lonmstalker.colearner.constants.Constants.DASH;
import static io.lonmstalker.colearner.constants.Constants.INVISIBLE;
import static org.apache.commons.lang3.StringUtils.SPACE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SplitConstants {
    public static final Splitter SPACE_SPLITTER = Splitter.on(SPACE);
    public static final Splitter DATA_SPLITTER = Splitter.on(DASH);
}
