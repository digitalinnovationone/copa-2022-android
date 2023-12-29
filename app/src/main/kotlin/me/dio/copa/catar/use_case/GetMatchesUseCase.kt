package me.dio.copa.catar.use_case

import kotlinx.coroutines.flow.Flow
import me.dio.copa.catar.domain.model.Match
import me.dio.copa.catar.domain.repositories.MatchesRepository
import javax.inject.Inject

//ir no reposítório; @inject faz com q a gente nao precise delcarar o usecase no modulos, será auto injetado
class GetMatchesUseCase @Inject constructor(
    private val repository: MatchesRepository
) {
    suspend operator fun invoke() : Flow<List<Match>> {
        return repository.getMatches()
    }
}