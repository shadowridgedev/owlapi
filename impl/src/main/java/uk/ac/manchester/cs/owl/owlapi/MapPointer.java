package uk.ac.manchester.cs.owl.owlapi;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.Internals;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLAxiomVisitorEx;
import org.semanticweb.owlapi.util.CollectionFactory;
import org.semanticweb.owlapi.util.MultiMap;

import uk.ac.manchester.cs.owl.owlapi.InitVisitorFactory.InitCollectionVisitor;
import uk.ac.manchester.cs.owl.owlapi.InitVisitorFactory.InitVisitor;

@SuppressWarnings("javadoc")
public class MapPointer<K, V extends OWLAxiom> implements Internals.Pointer<K, V>,
        Serializable {
    private static final long serialVersionUID = 40000L;
    private final MultiMap<K, V> map;
    private final AxiomType<?> type;
    private final OWLAxiomVisitorEx<?> visitor;
    private boolean initialized;
    protected final Internals i;

    public MapPointer(@Nullable AxiomType<?> t, @Nullable OWLAxiomVisitorEx<?> v,
            boolean initialized, @Nonnull Internals i) {
        type = t;
        visitor = v;
        map = new MultiMap<K, V>();
        this.initialized = initialized;
        this.i = checkNotNull(i);
    }

    public boolean isInitialized() {
        return initialized;
    }

    @SuppressWarnings("unchecked")
    public void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        if (visitor == null) {
            return;
        }
        if (visitor instanceof InitVisitor) {
            for (V ax : (Set<V>) i.getValues(i.getAxiomsByType(), type)) {
                K key = ax.accept((InitVisitor<K>) visitor);
                if (key != null) {
                    map.put(key, ax);
                }
            }
        } else {
            for (V ax : (Set<V>) i.getValues(i.getAxiomsByType(), type)) {
                Collection<K> keys = ax.accept((InitCollectionVisitor<K>) visitor);
                for (K key : keys) {
                    map.put(key, ax);
                }
            }
        }
    }

    @Override
    public String toString() {
        return initialized + map.toString();
    }

    public Set<K> keySet() {
        return CollectionFactory.getCopyOnRequestSetFromMutableCollection(map.keySet());
    }

    public Set<V> getValues(K key) {
        return CollectionFactory.getCopyOnRequestSetFromMutableCollection(map.get(key));
    }

    public boolean hasValues(K key) {
        return map.containsKey(key);
    }

    public boolean put(K key, V value) {
        return map.put(key, value);
    }

    public boolean remove(K key, V value) {
        return map.remove(key, value);
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public boolean contains(K key, V value) {
        return map.contains(key, value);
    }

    public Set<V> getAllValues() {
        return map.getAllValues();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.size() == 0;
    }
}
