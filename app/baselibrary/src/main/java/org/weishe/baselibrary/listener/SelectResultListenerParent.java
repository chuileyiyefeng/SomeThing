package org.weishe.baselibrary.listener;

public class SelectResultListenerParent {
    private SelectResultListener listener;
    private static SelectResultListenerParent parent;

    private SelectResultListenerParent() {
    }

    public SelectResultListener getListener() {
        return listener;
    }

    public void setListener(SelectResultListener listener) {
        this.listener = listener;
    }

    public static SelectResultListenerParent getInstance() {
        synchronized (SelectResultListenerParent.class) {
            if (parent==null) {
                parent=new SelectResultListenerParent();
            }
        }
        return parent;
    }
}
