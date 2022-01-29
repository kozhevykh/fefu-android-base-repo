package screens.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.activity.Base
import ru.fefu.activitytracker.databinding.PersonalActivityInfoBinding

class PersonalCardInfo : Base<PersonalActivityInfoBinding>(R.layout.personal_activity_info) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myInfoToolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

} 