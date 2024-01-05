package me.dio.copa.catar.features

import android.content.res.Resources.NotFoundException
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import me.dio.copa.catar.core.BaseViewModel
import me.dio.copa.catar.domain.model.MatchDomain
import me.dio.copa.catar.remote.UnexpectedException
import me.dio.copa.catar.use_case.GetMatchesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesUseCase
) : BaseViewModel<MainUiState, MainUiAction>(MainUiState()) {

    //sempre que abrir a activity, eu preciso dessa lista
    init {
        fetchMatches()
    }

    private fun fetchMatches() = viewModelScope.launch {
        getMatchesUseCase()
            .flowOn(Dispatchers.Main) //tela - main thread
            .catch {
                when (it) {
                    is NotFoundException -> sendAction(
                        MainUiAction.MatchesNotFound(
                            it.message ?: "Erro sem menssagem."
                        )
                    )

                    is UnexpectedException ->
                        sendAction(MainUiAction.Unexpected) //sem precisar instanciar pq é um object
                }
            }.collect {
                //pegar lista de dentro do flow
                setState {
                    copy(matches = it)
                }
            }
    }
}

//mostrar nossa lista - estado inicial -> lista vazia -> não tem processamento de dados
data class MainUiState(
    val matches: List<MatchDomain> = emptyList()
)

//colocar alguma coisa que pode acontecer ali
sealed class MainUiAction {
    data class MatchesNotFound(val message: String) : MainUiAction()
    object Unexpected : MainUiAction()
}