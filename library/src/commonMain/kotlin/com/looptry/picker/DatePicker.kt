package com.looptry.picker

import androidx.compose.runtime.*
import kotlinx.datetime.*

enum class DateType {
    YEAR,
    MONTH,
    DAY
}

@Composable
fun AppDatePicker(
    visible: Boolean,
    value: LocalDate? = null,
    type: DateType = DateType.DAY,
    start: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        .minus(DatePeriod(years = 50)),
    end: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        .plus(DatePeriod(years = 10)),
    onCancel: () -> Unit,
    onChange: (LocalDate) -> Unit
) {
    require(end > start) {
        "Invalid date range: end ($end) must be after start ($start)"
    }

    var rangesSource by rememberRangesSource(start, end, type)
    val currentRanges by rememberCurrentRanges(rangesSource)
    var currentValues by rememberCurrentValues(rangesSource, value, start, end)

    WePicker(
        visible,
        currentRanges,
        values = currentValues,
        title = "选择日期",
        onCancel = onCancel,
        onColumnValueChange = { column, _, newValues ->
            handleColumnChange(rangesSource, newValues, column, start, end, type) {
                rangesSource = it
            }
        }
    ) {
        currentValues = it

        val date = LocalDate(
            year = rangesSource[0][it[0]],
            monthNumber = rangesSource.getOrNull(1)?.get(it[1]) ?: 1,
            dayOfMonth = rangesSource.getOrNull(2)?.get(it[2]) ?: 1
        )
        onChange(date)
    }
}

private fun handleColumnChange(
    ranges: Array<List<Int>>,
    values: Array<Int>,
    column: Int = 0,
    start: LocalDate,
    end: LocalDate,
    type: DateType,
    onRangesChange: (Array<List<Int>>) -> Unit
) {
    if (type == DateType.YEAR || column == 2) return

    ranges[1] = calculateMonthRange(values.first(), start, end, ranges.first().lastIndex)
    if (type == DateType.DAY) {
        ranges[1].getOrNull(values[1])?.let { month ->
            ranges[2] =
                calculateDayRange(values.first(), month, start, end, ranges.first().lastIndex)
        }
    }

    onRangesChange(ranges.copyOf())
}

private fun calculateMonthRange(
    yearIndex: Int,
    start: LocalDate,
    end: LocalDate,
    yearLastIndex: Int
): List<Int> {
    return when {
        yearIndex == 0 && yearIndex == yearLastIndex -> (start.monthNumber..end.monthNumber).toList()
        yearIndex == 0 -> (start.monthNumber..12).toList()
        yearIndex == yearLastIndex -> (1..end.monthNumber).toList()
        else -> (1..12).toList()
    }
}

private fun calculateDayRange(
    yearIndex: Int,
    month: Int,
    start: LocalDate,
    end: LocalDate,
    yearLastIndex: Int
): List<Int> {
    return when {
        yearIndex == yearLastIndex && start.monthNumber == end.monthNumber -> (start.dayOfMonth..end.dayOfMonth).toList()
        yearIndex == 0 && month == start.monthNumber -> (start.dayOfMonth..getDaysInMonth(
            start.year,
            start.monthNumber
        )).toList()

        yearIndex == yearLastIndex && month == end.monthNumber -> (1..end.dayOfMonth).toList()
        else -> (1..getDaysInMonth(start.year + yearIndex, month)).toList()
    }
}

@Composable
private fun rememberRangesSource(
    start: LocalDate,
    end: LocalDate,
    type: DateType
): MutableState<Array<List<Int>>> {
    return remember(start, end, type) {
        val options = arrayOf(
            (start.year..end.year).toList(),
            (1..12).toList(),
            (1..31).toList()
        )

        mutableStateOf(
            when (type) {
                DateType.YEAR -> options.copyOfRange(0, 1)
                DateType.MONTH -> options.copyOfRange(0, 2)
                DateType.DAY -> options
            }
        )
    }
}

@Composable
private fun rememberCurrentRanges(
    ranges: Array<List<Int>>
): State<Array<List<String>>> {
    val units = remember { arrayOf("年", "月", "日") }
    val currentRanges by rememberUpdatedState(newValue = ranges)

    return remember {
        derivedStateOf {
            currentRanges.mapIndexed { index, options ->
                val unit = units[index]
                options.map { it.toString() + unit }
            }.toTypedArray()
        }
    }
}

@Composable
private fun rememberCurrentValues(
    ranges: Array<List<Int>>,
    value: LocalDate?,
    start: LocalDate,
    end: LocalDate
): MutableState<Array<Int>> {
    return remember(ranges, value, start, end) {
        val finalValue = value ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
        val initialValue = when {
            finalValue < start -> start
            finalValue > end -> end
            else -> finalValue
        }

        mutableStateOf(
            arrayOf(
                ranges.first().indexOf(initialValue.year),
                ranges.getOrNull(1)?.indexOf(initialValue.monthNumber) ?: 0,
                ranges.getOrNull(2)?.indexOf(initialValue.dayOfMonth) ?: 0
            )
        )
    }
}

@Stable
interface DatePickerState {
    val visible: Boolean

    fun show(
        value: LocalDate? = null,
        type: DateType = DateType.DAY,
        start: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
            .minus(DatePeriod(years = 50)),
        end: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
            .plus(DatePeriod(years = 10)),
        onChange: (LocalDate) -> Unit
    )

    fun hide()
}

@Composable
fun rememberDatePickerState(): DatePickerState {
    val state = remember { DatePickerStateImpl() }

    state.props?.let { props ->
        AppDatePicker(
            visible = state.visible,
            value = props.value,
            type = props.type,
            start = props.start,
            end = props.end,
            onCancel = { state.hide() },
            onChange = props.onChange
        )
    }

    return state
}

private class DatePickerStateImpl : DatePickerState {
    override var visible by mutableStateOf(false)
    var props by mutableStateOf<DatePickerProps?>(null)
        private set

    override fun show(
        value: LocalDate?,
        type: DateType,
        start: LocalDate,
        end: LocalDate,
        onChange: (LocalDate) -> Unit
    ) {
        props = DatePickerProps(value, type, start, end, onChange)
        visible = true
    }

    override fun hide() {
        visible = false
    }
}

private data class DatePickerProps(
    val value: LocalDate?,
    val type: DateType,
    val start: LocalDate,
    val end: LocalDate,
    val onChange: (LocalDate) -> Unit
)

private fun getDaysInMonth(year: Int, month: Int): Int {
    return when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (isLeapYear(year)) 29 else 28
        else -> throw IllegalArgumentException("Invalid month: $month")
    }
}

private fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}