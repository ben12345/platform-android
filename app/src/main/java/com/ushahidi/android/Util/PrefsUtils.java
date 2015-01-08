/*
 * Copyright (c) 2014 Ushahidi.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program in the file LICENSE-AGPL. If not, see
 * https://www.gnu.org/licenses/agpl-3.0.html
 */

package com.ushahidi.android.Util;

/**
 * @author Ushahidi Team <team@ushahidi.com>
 */
public class PrefsUtils {

    private static final int ONE_HOUR = 3600000;

    private static final int ONE_MINUTE = 60000;

    public static long calculateInterval(String time) {
        String[] pieces = time.split(":");
        return Integer.parseInt(pieces[0]) * ONE_HOUR +
                Integer.parseInt(pieces[1]) * ONE_MINUTE;
    }
}
