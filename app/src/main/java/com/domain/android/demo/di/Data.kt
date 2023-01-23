import com.domain.android.demo.data.Repository
import com.domain.android.demo.data.RepositoryImpl
import org.koin.dsl.module

val repository = module {
    factory<Repository> {
        RepositoryImpl(get())
    }
}

