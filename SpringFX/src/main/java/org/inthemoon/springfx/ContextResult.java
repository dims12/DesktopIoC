package org.inthemoon.springfx;

import java.util.Optional;

/**
 * Created by Dims on 18.04.2017.
 */
public class ContextResult<R> {

   private R result;

   public R getResult() {
      return result;
   }

   public void setResult(R result) {
      this.result = result;
   }
}
