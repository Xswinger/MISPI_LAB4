package webdeving.beans;

import webdeving.dataBase.HitService;
import webdeving.entity.Hit;
import webdeving.verify.Verifier;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@ManagedBean(name="appBean")
@ApplicationScoped
public class AppBean implements Serializable {
    private final Verifier verifier = new Verifier();
    private final HitService hitService = new HitService();
    private Hit hit = new Hit();
    private List<Hit> hits = hitService.getAll();


    public void add() {
        long start = System.nanoTime();
        if (!verifier.verifyR(hit.getR())) {
            return;
        }
        boolean success = verifier.verifyHit(hit.getX(), hit.getY(), hit.getR());
        hit.setSuccess(success);
        hit.setDate(LocalDateTime.now());
        hit.setExTime(System.nanoTime() - start);
        hitService.add(hit);
        hits.add(hit);
        hit = new Hit();
    }

    public Hit getHit() {
        return hit;
    }

    public void setHit(Hit hit) {
        this.hit = hit;
    }

    public List<Hit> getHitList() {
        return hits;
    }

    public void setHitList(List<Hit> hits) {
        this.hits = hits;
    }

    public void clear() {
        hitService.clear();
        this.hits.clear();
    }

}
