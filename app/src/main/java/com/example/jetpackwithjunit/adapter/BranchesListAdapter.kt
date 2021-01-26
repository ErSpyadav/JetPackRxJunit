package com.example.jetpackwithjunit.adapter

class BranchesListAdapter {
}
/*
class BranchesListAdapter(context: Context) : BaseDataBindingAdapter() {

    val context = context

    var items = mutableListOf<UIBranchListItem>()

    var onItemClick: ((UIBranchListItem) -> Unit)? = null

    override fun getItemForPosition(position: Int): Any = items[position]

    override fun getListener(): Any? = onItemClick

    override fun getLayoutIdForPosition(position: Int): Int = fragment_branch_locator_list_element

    override fun getItemCount(): Int = items.size

    fun setItemsAndNotify(newItems: List<UIBranchListItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val lItem = holder.itemView.component_branch_locator_list_item_status_image
        lItem.setImageDrawable(ContextCompat
                .getDrawable(context, items[position].statusImage!!)?.mutate())

        if (items[position].color.equals(BranchListItemUIMapper.BLUE_LIGHT) ||
                !items[position].message.isNullOrEmpty()) {
            tintStatusBlue(holder)
        } else if (items[position].color.equals(BranchListItemUIMapper.GRAY_DARK)) {
            tintStatusBlack(holder)
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(items[position])!!
        }
    }

    private fun tintStatusBlue(holder: DataBindingViewHolder) {
        holder.itemView.component_branch_locator_list_item_status_text
                .setTextColor(ContextCompat.getColor(context, R.color.tsb_marine))
        holder.itemView.component_branch_locator_list_item_status_image.drawable.setColorFilter(
                ContextCompat.getColor(context, R.color.tsb_marine), PorterDuff.Mode.SRC_ATOP)
    }

    private fun tintStatusBlack(holder: DataBindingViewHolder) {
        holder.itemView.component_branch_locator_list_item_status_text
                .setTextColor(ContextCompat.getColor(context, R.color.neutral_tsb_hint))

        holder.itemView.component_branch_locator_list_item_status_text.typeface = Typeface.DEFAULT_BOLD

        holder.itemView.component_branch_locator_list_item_status_image.drawable.setColorFilter(
                ContextCompat.getColor(context, R.color.neutral_tsb_hint), PorterDuff.Mode.SRC_ATOP)
    }
}

 */