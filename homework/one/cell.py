rules: { String: Boolean } = {
  "000": True,
  "001": False,
  "010": False,
  "100": False,
  # ...
}

# [true, false, false, true ...]
def updateCell(tape: Int[], index: Int) {
    state = ("1" if tape[index-1] else "0") + tape[index] + tape[index+1]
    tape[index] = rules[state]:
}
