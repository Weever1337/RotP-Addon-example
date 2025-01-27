package com.rotpaddon.exampleaddon.action.non_stand

import com.github.standobyte.jojo.action.non_stand.NonStandAction

abstract class TutorialAction(builder: Builder) : NonStandAction(builder) {
    open class Builder : NonStandAction.Builder() {
        override fun holdType() : NonStandAction.Builder = this
        override fun needsFreeMainHand() : NonStandAction.Builder = this
        override fun energyCost(energy: Float): NonStandAction.Builder = super.energyCost(energy)
        override fun cooldown(cooldown: Int): NonStandAction.Builder = super.cooldown(cooldown)
    }
}