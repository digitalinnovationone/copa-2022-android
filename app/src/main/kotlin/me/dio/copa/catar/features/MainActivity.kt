package me.dio.copa.catar.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import me.dio.copa.catar.extensions.observe
import me.dio.copa.catar.notification.scheduler.extensions.NotificationMatcherWorker
import me.dio.copa.catar.ui.theme.Copa2022Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //consumir viewmodel
    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //observar as actions
        observeActions()
        setContent {
            Copa2022Theme {
              val state by viewModel.state.collectAsState()
               MainScreen(matches = state.matches, viewModel::toggleNotification ) //passando uma função como referência
            }
        }
    }

    private fun observeActions() {
        viewModel.action.observe(this) {action ->
            when(action) {
                is MainUiAction.DisableNotification ->
                    //chamada do worker;
                    //passar o appcontext para evitar o memory leak ->
                    //referência da activity fica presa/não é "limpa" depois que termina o uso dela -> consumir recurso
                NotificationMatcherWorker.cancel(applicationContext, action.match)

                is MainUiAction.EnableNotification ->
                    NotificationMatcherWorker.start(applicationContext, action.match)

                is MainUiAction.MatchesNotFound -> TODO()
                MainUiAction.Unexpected -> TODO()
            }

        }
    }

}

