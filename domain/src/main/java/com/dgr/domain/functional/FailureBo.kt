package com.dgr.domain.functional

sealed class FailureBo {
    object NetworkConnection: FailureBo()
    object ServerError: FailureBo()
    object UnexpectedFailure: FailureBo()

    abstract class FeatureFailure: FailureBo()
}