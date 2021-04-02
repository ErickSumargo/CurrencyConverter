package com.bael.interview.lib.database.di.qualifier

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Qualifier
@Retention(BINARY)
internal annotation class DatabaseNameQualifier
