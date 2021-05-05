package will.sudoku.solver

class SimpleCandidateEliminator : CandidateEliminator {
    override fun eliminate(board: Board): Boolean {
        var anyUpdate = false
        var stable: Boolean
        do {
            stable = true
            for (coord in Coord.all) {
                for (coordGroup in CoordGroup.all) {
                    if (board.isConfirmed(coord)) {
                        for (peerCoord in coordGroup.coords) {
                            val updated = (coord != peerCoord) &&
                                    board.eraseCandidatePattern(peerCoord, board.candidatePattern(coord))
                            if (updated) {
                                anyUpdate = true
                                stable = false
                            }
                        }
                    }
                }
            }
        } while (!stable)
        return anyUpdate
    }
}