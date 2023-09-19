package me.dio.copa.catar.domain.usecase

import me.dio.copa.catar.domain.model.Match
import me.dio.copa.catar.domain.repositories.MatchesRepository
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(
    private  val repository: MatchesRepository
) {
    suspend operator fun invoke() :kotlinx.coroutines.flow.Flow<List<Match>> {
        return repository.getMatches()
    }
}