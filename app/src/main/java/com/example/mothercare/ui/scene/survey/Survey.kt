package com.example.mothercare.ui.scene.survey


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mothercare.R
import com.example.mothercare.ui.scene.survey.questions.MultipleChoiceQuestion
import com.example.mothercare.ui.scene.survey.questions.SingleChoiceQuestion
import com.example.mothercare.ui.scene.survey.questions.SliderQuestion
import com.example.mothercare.ui.scene.survey.questions.Superhero

@Composable
fun FreeTimeQuestion(
   /* selectedAnswers: List<Int>,
    onOptionSelected: (selected: Boolean, answer: Int) -> Unit,
    modifier: Modifier = Modifier,*/
   selectedAnswers: Superhero?,
   onOptionSelected: (Superhero) -> Unit,
   modifier: Modifier = Modifier,

) {
    /*MultipleChoiceQuestion(
        titleResourceId = R.string.how_would_you_describe_your_overall_health,
        directionsResourceId = R.string.select_all,
        possibleAnswers = listOf(
            R.string.excellent,
            R.string.good,
            R.string.fair,
            R.string.poor,
            R.string.i_do_not_know
        ),
        selectedAnswers = selectedAnswers,
        onOptionSelected = onOptionSelected,
        modifier = modifier,
    )*/

    SingleChoiceQuestion(
        titleResourceId = R.string.how_would_you_describe_your_overall_health,
        directionsResourceId = R.string.select_one,
        possibleAnswers = listOf(
            Superhero(R.string.excellent, R.drawable.spark),
            Superhero(R.string.good, R.drawable.lenz),
            Superhero(R.string.fair, R.drawable.bug_of_chaos),
            Superhero(R.string.poor, R.drawable.frag),
            Superhero(R.string.i_do_not_know, R.drawable.spark),
        ),
        selectedAnswer = selectedAnswers,
        onOptionSelected = onOptionSelected,
        modifier = modifier,
    )
}

@Composable
fun SuperheroQuestion(
    selectedAnswer: Superhero?,
    onOptionSelected: (Superhero) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceQuestion(
        titleResourceId = R.string.do_you_have_excessive_severe_pain_or_excessive_bleeding_during_your_periods,
        directionsResourceId = R.string.select_one,
        possibleAnswers = listOf(
            Superhero(R.string.always, R.drawable.spark),
            Superhero(R.string.often, R.drawable.lenz),
            Superhero(R.string.sometimes, R.drawable.bug_of_chaos),
            Superhero(R.string.never, R.drawable.frag),
        ),
        selectedAnswer = selectedAnswer,
        onOptionSelected = onOptionSelected,
        modifier = modifier,
    )
}


@Composable
fun FeelingAboutSelfiesQuestion(
    value: Float?,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    /*SliderQuestion(
        titleResourceId = R.string.selfies,
        value = value,
        onValueChange = onValueChange,
        startTextResource = R.string.strongly_dislike,
        neutralTextResource = R.string.neutral,
        endTextResource = R.string.strongly_like,
        modifier = modifier,
    )*/

    SliderQuestion(
        titleResourceId = R.string.are_you_aware_of_the_risks_associated_with_pregnancy_and_childbirth,
        value = value,
        onValueChange = onValueChange,
        startTextResource = R.string.no,
        neutralTextResource = R.string.somewhat,
        endTextResource = R.string.yes,
        modifier = modifier,
    )

}

