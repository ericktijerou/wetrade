/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.util

import com.example.androiddevchallenge.R

object DataHelper {
    val listPositions by lazy {
        listOf(
            PositionItem(
                title = "ALK",
                image = R.drawable.ic_home_alk,
                subtitle = "Alaska Air Group, Inc.",
                value = "$7,918",
                percentage = -0.54
            ),
            PositionItem(
                title = "BA",
                image = R.drawable.ic_home_ba,
                subtitle = "Boeing Co.",
                value = "$1,293",
                percentage = 4.18
            ),
            PositionItem(
                title = "DAL",
                image = R.drawable.ic_home_dal,
                subtitle = "Delta Airlines Inc.",
                value = "$893.50",
                percentage = -0.54
            ),
            PositionItem(
                title = "EXPE",
                image = R.drawable.ic_home_exp,
                subtitle = "Expedia Group Inc.",
                value = "$12,301",
                percentage = 2.51
            ),
            PositionItem(
                title = "EADSY",
                image = R.drawable.ic_home_eadsy,
                subtitle = "Airbus SE",
                value = "$12,301",
                percentage = 1.38
            ),
            PositionItem(
                title = "JBLU",
                image = R.drawable.ic_home_jblu,
                subtitle = "Jetblue Airways Corp.",
                value = "$8,521",
                percentage = 1.56
            ),
            PositionItem(
                title = "MAR",
                image = R.drawable.ic_home_mar,
                subtitle = "Marriott International Inc.",
                value = "$521",
                percentage = 2.75
            ),
            PositionItem(
                title = "CCL",
                image = R.drawable.ic_home_ccl,
                subtitle = "Carnival Corp",
                value = "$5,481",
                percentage = 0.14
            ),
            PositionItem(
                title = "RCL",
                image = R.drawable.ic_home_rcl,
                subtitle = "Royal Caribbean Cruises",
                value = "$9,184",
                percentage = 1.69
            ),
            PositionItem(
                title = "TRVL",
                image = R.drawable.ic_home_trvl,
                subtitle = "Travelocity Inc.",
                value = "$654",
                percentage = 3.23
            )
        )
    }
}
