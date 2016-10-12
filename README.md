# IconHandler
This class was created to use in Android projects.

# Porpouse
Handles the drawable binding and position on View, ImageView or TextView. Also can change the icon color, alpha and size using a SelectorDrawable. 


# How to use it

    --Setting up a icon on the ImageView.
    Icon.put(yourImageView, R.mipmap.your_icon);
    
    
    --Setting up a icon on backgrond of a View.
    Icon.put(yourView, R.mipmap.your_icon);
    
    
    --Setting up a icon on the left of the TextView. Also can use right(), top() and bottom() methods.
    Icon.left(yourTextView, R.mipmap.your_icon);
       
       
    --Setting up a icon to the ImageView and converting it to blue.
    Icon.on(yourImageView).blue(R.mipmap.your_icon).put();
    Icon.top(yourTextView).gray(R.mipmap.your_icon).put();
    
    
    --You can combine the usage with colors transformations.
    --You can also modify the class to create your own colors.
    Icon.on(yourImageView).black(R.mipmap.your_icon).put();
    
    
    --Setting up a icon to the ImageView and applying alpha. (0-255)
    Icon.on(yourImageView).alpha(130).put();
    
    
    --Will make the icon appears weak. Receiving focus will reveals the real color of icon.
     Icon.focus(yourEditText, R.mipmap.your_icon, Gravity.RIGHT);
    
    
    --Clearing all icons of the View.
    Icon.clear(yourImageView);
    
    
