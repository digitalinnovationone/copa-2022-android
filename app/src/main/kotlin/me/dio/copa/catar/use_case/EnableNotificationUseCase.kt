package me.dio.copa.catar.use_case

import me.dio.copa.catar.domain.repositories.MatchesRepository
import javax.inject.Inject

class EnableNotificationUseCase @Inject constructor(
    private val repository: MatchesRepository
) {
    suspend operator fun invoke(id: String) {
        repository.enableNotificationFor(id)
    }
}